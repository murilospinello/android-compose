package com.murilospinello2025.andela_test

import junit.framework.TestCase.assertTrue
import org.junit.Test

class AndelaTest {
    fun isValid(braces: String): Boolean {
        val stack = mutableListOf<Char>()
        val pairs = mapOf(')' to '(', ']' to '[', '}' to '{')

        for (c in braces) {
            if (c in pairs.values) {
                stack.add(c)
            } else if (c in pairs.keys) {
                if (stack.isEmpty() || stack.removeAt(stack.lastIndex) != pairs[c])
                    return false
            }
        }

        return stack.isEmpty()
    }


    fun isValid2(braces: String): Boolean {
        val regex = Regex(".*\\{.*\\[.*\\(.*\\).*\\].*\\}.*")
        return regex.matches(braces)

    }

    fun reverseString(str: String): String {
        return str.reversed()
    }

    fun allSum(nums: IntArray, target: Int): List<Pair<Int, Int>> {

        val sum = mutableListOf<Pair<Int, Int>>()
        for (i in nums.indices) {
            for (j in nums.indices) {
                if (i != j && nums[i] + nums[j] == target)
                    sum.add(nums[i] to nums[j])
            }
        }

        return sum.toList()
    }

    fun <T> uniqueKeepOrder(items: List<T>): List<T> {

        val seen = hashSetOf<T>()
        val unique = mutableListOf<T>()

        for (item in items)
            if (seen.add(item))
                unique.add(item)


        return unique.toList()
    }

    fun groupAnagrams(words: List<String>): List<List<String>> {
        val map = HashMap<String, MutableList<String>>()
        for (w in words) {
            val key = w.toCharArray().sorted().joinToString("")
            map.getOrPut(key) { mutableListOf() }.add(w)
        }
        return map.values.map { it.toList() }
    }

    data class Interval(val start: Int, var end: Int)

    fun mergeIntervals(intervals: List<Interval>): List<Interval> {
        val merged = mutableListOf<Interval>()

        if (intervals.isNotEmpty()) {
            val sorted = intervals.sortedBy { it.start }
            var positionToCheck = intervals.first()

            for (index in 1 until sorted.size) {
                if (sorted[index].start <= positionToCheck.end) {
                    positionToCheck.end = maxOf(positionToCheck.end, sorted[index].end)
                } else {
                    merged.add(positionToCheck)
                    positionToCheck = sorted[index]
                }
            }
            merged.add(positionToCheck)
        }
        return merged.toList()
    }

    @Test
    fun testIsValid() {
        assertTrue(isValid2("x{a[b(c)]}d]"))
        assertTrue(isValid("{a[b(c)]}d"))
        assertTrue(reverseString("abc") == "cba")
        assertTrue(
            allSum(
                nums = intArrayOf(0, 1, 2, 3), target = 3
            ) == listOf((0 to 3), (1 to 2), (2 to 1), (3 to 0))
        )

        assertTrue(uniqueKeepOrder(listOf(1, 2, 2, 3, 4)) == listOf(1, 2, 3, 4))
        assertTrue(uniqueKeepOrder("abcc".toList()) == "abc".toList())

        assertTrue(
            groupAnagrams(listOf("eat", "tea", "tan", "ate", "nat", "bat")) == arrayListOf(
                arrayListOf("eat", "tea", "ate"), arrayListOf("bat"), arrayListOf("tan", "nat")
            )
        )

        assertTrue(
            mergeIntervals(
                listOf(
                    Interval(1, 3),
                    Interval(2, 6),
                    Interval(8, 10),
                    Interval(15, 18)
                )
            ) == listOf(
                Interval(1, 6),
                Interval(8, 10),
                Interval(15, 18)
            )
        )
    }
}