package dev.cloudy.homes.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;

/**
 * @author Emmy
 * @project Homes
 * @date 31/10/2024 - 10:18
 */
@UtilityClass
public class CharUtil {
    /**
     * List of non-allowed characters
     */
    public String[] nonAllowedChars = Arrays.asList(
            ".", ",", ":", ";", "'", "\"", "!", "?", "(", ")", "[", "]", "{", "}", "<", ">", "/", "\\", "|", "@", "#", "$", "%", "^", "&", "*", "+", "=", "~", "`"
    ).toArray(new String[0]);
}