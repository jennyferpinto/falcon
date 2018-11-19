package io.falcon.assignment;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="payload")
public class Payload extends Model {

    @Id
    @GeneratedValue(generator = "payload_generator")
    @SequenceGenerator(
            name = "payload_generator",
            sequenceName = "payload_sequence",
            initialValue = 1
    )
    private Long payloadId;

    @NotBlank
    @Size(min = 2, max = 100)
    private String content;

    @Column(name = "longest_palindrome_size", nullable = true)
    private Integer longest_palindrome_size;

    public String getContent() {
        return this.content;
    }

    public Long getPayloadId(){
        return payloadId;
    }

    public Integer getPalindromeLength(){
        return longest_palindrome_size;
    }

    public void setPalindromeLength(String content){
        Set<String> l = findPalindromes(content);
        longest_palindrome_size = getLargestPalindrome(l);
    }

    public Set<String> findPalindromes(String content){ 
        // loops through substrings in content to look for palindromes
        
        Set<String> palindromeSet = new HashSet<>(); // sets prevent duplicate palindromes to be saved

        for (int left = 0; left < content.length(); left++) {
            for ( int right = left + 1; right < content.length(); right++){
                String pal = content.substring(left, right+1);  // substring non inclusive end point
                if (isPalindrome(pal)) {
                    palindromeSet.add(pal);
                }
            }
        }
        return palindromeSet;
    }

    // loop 1 --> 0:1 ra // 0 2 rac // 0:3 race // 0:4 racec // 0:5 raceca // 0:6 racecar 
    // loop 2 --> 1:2 ac // 1:3 ace // 1:4 acec // 1:5 aceca // 1:6 acecar 

    static boolean isPalindrome(String s){ // checks whether the substring is a palindrome
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length/2; i++){
            if (chars[i] != chars[chars.length - 1 - i]){
                return false;
            }
        }
        return true;
    }

    public static Integer getLargestPalindrome(Set<String> set) {
        int max = 0;
        String longestString = null;
        
        for (String s : set) {
            if (s.length() > max) {
                max = s.length();
                longestString = s;
            }
        }

        if (longestString == null) { 
            return 0; 
        }

        return longestString.length();
    }

    @Override
    public String toString() {
        return String.format(
                "Payload[i=%d, content='%s', timestamp='%s', longest_palindrome_length='%d']", 
                payloadId, content, Payload.super.getTimestamp(), longest_palindrome_size);
    }
}