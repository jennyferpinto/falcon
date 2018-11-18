package io.falcon.assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
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

    // @GeneratedValue(strategy=GenerationType.IDENTITY)
    // private Long id;
    // private String content;
    // private String timestamp;
    
    // public Payload() {
    // }
    
    // public Payload ( String content, String timestamp) {
    //     this.content = content;
    //     this.timestamp = timestamp;
    // }

    @NotBlank
    @Size(min = 2, max = 100)
    private String content;

    @Column(name = "longest_palindrome_size", nullable = true)
    private Integer longest_palindrome_size;

    public String getContent() {
        return this.content;
    }

    public void setPalindromeLength(String content){
        Set<String> l = findPalindromes(content);
        longest_palindrome_size = getLargestPalindrome(l);
        //longest_palindrome_size = getLargestPalindrome(findPalindromes(content));
    }

    public Integer getPalindromeLength(){
        return longest_palindrome_size;
    }

    public Long getPayloadId(){
        return payloadId;
    }

    public Set<String> findPalindromes(String content){
        
        Set<String> palindromeSet = new HashSet<>();

        for (int left = 0; left < content.length(); left++) {

            for ( int right = left + 1; right < content.length(); right++){
                String pal = content.substring(left, right+1); 
                
                if (isPalindrome(pal)) {
                    palindromeSet.add(pal);
                }
            }
        }
        return palindromeSet;
    }

    // round 1 --> 0 1 ra // 0 2 rac // 0 3 race // 0 4 racec // 0 5 raceca // 0 6 racecar -- left = 0, right = 6
    // round 2 --> 1 2 ac // 1 3 ace // 

    static boolean isPalindrome(String s){
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
        return longestString.length();
    }

    @Override
    public String toString() {
        return String.format(
                "Payload[i=%d, content='%s', createdAt='%s', longest_palindrome_length='%d']", 
                payloadId, content, Payload.super.getCreatedAt(), longest_palindrome_size);
    }
}