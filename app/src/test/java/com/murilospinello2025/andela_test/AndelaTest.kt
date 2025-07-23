package com.murilospinello2025.andela_test

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class AndelaTest {
    fun isValid(braces: String): Boolean {
        val stack = mutableListOf<Char>()
        val pairs = mapOf(')' to '(' , ']' to '[' , '}' to '{' )

        for(c in braces){
            if(c in pairs.values){
                stack.add(c)
            } else if(c in pairs.keys) {
                if(stack.isEmpty() || stack.removeAt(stack.lastIndex) != pairs[c])
                    return false
            }
        }

        return stack.isEmpty()
    }

    @Test
    fun testIsValid() {
        assertTrue(isValid("(){}[]"))
        assertFalse(isValid("(]"))
        assertFalse(isValid("([)]"))
        assertTrue(isValid("{[]}"))
    }
}