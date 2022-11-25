package com.milanradosavac.util.ext

import kotlin.math.pow

/**
 * Performs a replacement of each letter with its position in the alphabet, then squares the sum of 2 instances of the resulting number
 * @return The resulting [String]
 * @author Milan Radosavac
 */
fun String.jumble(): String {
    var returnString = ""

    var string = this

    if (this.length <= 4) string += this

    run breaking@ {
        string.forEachIndexed { i, c ->

            if (i == 5) return@breaking

            ('A'..'Z').forEachIndexed { index, c1 ->
                if(c1 == c.uppercaseChar()) {
                    returnString += index
                }
            }

            if(c.isDigit()) {
                returnString += c
            }
        }
    }

    return ((returnString.toInt() + returnString.toInt()).toDouble().pow(2.00)).toString().filter { it != 'E' && it != '.' }
}