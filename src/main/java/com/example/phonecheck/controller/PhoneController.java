package com.example.phonecheck.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PhoneController {

    @RequestMapping(value ="/list", method = RequestMethod.POST)
    public ResponseEntity<?> comb(@RequestBody String number){
        List<String> variation = characterVariation(number);
        return new ResponseEntity<>(variation, HttpStatus.OK);
    }

    public List<String> characterVariation(String phoneNum) {
        HashMap<Character, char[]> keypad = new HashMap<Character, char[]>();
        keypad.put('0', new char[] { '0' });
        keypad.put('1', new char[] { '1' });
        keypad.put('2', new char[] { 'a', 'b', 'c' });
        keypad.put('3', new char[] { 'd', 'e', 'f' });
        keypad.put('4', new char[] { 'g', 'h', 'i' });
        keypad.put('5', new char[] { 'j', 'k', 'l' });
        keypad.put('6', new char[] { 'm', 'n', 'o' });
        keypad.put('7', new char[] { 'p', 'q', 'r', 's' });
        keypad.put('8', new char[] { 't', 'u', 'v' });
        keypad.put('9', new char[] { 'w', 'x', 'y', 'z' });
        List<String> result = new ArrayList<String>();
        if (phoneNum == null || phoneNum.length() == 0) {
            return result;
        }
        char[] arr = new char[phoneNum.length()];
        transfer(phoneNum, 0, keypad, result, arr);

        return result;
    }

    private void transfer(String phoneNum, int index, HashMap<Character, char[]> keypad, List<String> result, char[] arr) {
        if (index == phoneNum.length()) {
            result.add(new String(arr));
            return;
        }
        char number = phoneNum.charAt(index);
        char[] candidates = keypad.get(number);
        for (int i = 0; i < candidates.length; i++) {
            arr[index] = candidates[i];
            transfer(phoneNum, index + 1, keypad, result, arr);
        }
    }
}
