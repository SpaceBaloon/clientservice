package com.clientservice.misc;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 *
 * @author BelkinSergei
 */
public class GroupFormatter {
    
    public static final String MATCH_PATTERN = ".*[A-Z]+.*";;

    private final String pattern;
    private final String text;
    final Map<Character, Queue<Character>> table = new HashMap<>();

    private GroupFormatter(String pattern, String text ) {
        this.pattern = pattern;
        this.text = text;
    }
    
    public static GroupFormatter getForPattern( String pattern, String text ) {
        if( pattern == null || pattern.trim().isEmpty() 
                || text == null || text.trim().isEmpty() )
            throw new IllegalArgumentException( "Parameters can't be null or empty." );
        if( !checkPattern( pattern ) )
            throw new IllegalArgumentException( "Pattern " + pattern + " is not correct." );
        return new GroupFormatter( pattern, text );
    }
    
    static boolean checkPattern( String pattern ) {
        return pattern.matches( MATCH_PATTERN );
    }
    
    public String convertToPattern( String toPattern ) {
        if( !checkPattern( toPattern ) ) 
            throw new IllegalArgumentException( "Pattern " + pattern + " is not correct." );
        parseText();
        return convertToPattern0( toPattern );
    }

    void parseText() {
        final int textLength = text.length();
        final int patternLength = pattern.length();
        for( int i=0,j=0; i<textLength && j<patternLength; i++,j++ ) {
            while( i<textLength && !Character.isDigit( text.charAt( i ) ) ) i++;
            char ch = pattern.charAt( j );
            while( j<patternLength && !( 'A' <= ch && ch <= 'Z' ) ) ch = pattern.charAt( ++j );
            Queue<Character> q = table.get( ch );
            if( q == null ) {
                q = new LinkedList<>();
                table.put(ch, q);
            }
            q.add( text.charAt( i ) );
        }
    }

    String convertToPattern0(String toPattern) {
        StringBuilder buffer = new StringBuilder( toPattern.length() );
        final char[] chars = toPattern.toCharArray();
        for( Character ch : chars ) {
            if( table.containsKey( ch ) ) {
                Queue<Character> q = table.get( ch );
                buffer.append( q.remove() );
            } else {
                buffer.append( ch );
            }
        }
        return buffer.toString();
    }
    
}
