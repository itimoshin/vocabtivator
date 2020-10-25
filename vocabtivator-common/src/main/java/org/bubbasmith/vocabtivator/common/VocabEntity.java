package org.bubbasmith.vocabtivator.common;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Value
@AllArgsConstructor
public class VocabEntity {
    String value;

    public String getFilteredValue() {
        return value.replaceAll("\\s?\\(.*\\)\\s?", "");
    }

    public List<String> getWithExamples() {
        Matcher matcher = Pattern.compile("\\[(.*)\\]").matcher(value);
        List<List<MatchResult>> matchResultsMatrix = new ArrayList<>();
        while (matcher.find()) {
            List<MatchResult> matchResultsForGroup = Stream.of(matcher.group(1).split("\\s?,\\s?"))
                    .map(word -> new MatchResult(matcher.start(), matcher.end(), word))
                    .collect(Collectors.toList());
            matchResultsMatrix.add(matchResultsForGroup);
        }

        rotateAndAdjust(matchResultsMatrix);

        return null;
    }

    private List<List<MatchResult>> rotateAndAdjust(List<List<MatchResult>> inputMatrix) {

        // Adjust all rows to the same size (by adding nulls)
        int maxLen = inputMatrix.stream().map(List::size).max(Integer::compareTo).orElse(0);
        int inputMatrixSize = inputMatrix.size();
        for (List<MatchResult> row : inputMatrix) {
            for (int i = 0; i < row.size() - maxLen; i++) {
                row.add(null);
            }
        }

        // Rotate the matrix

        List<List<MatchResult>> rotatedMatrix = new ArrayList<>(maxLen);
        for (int i = 0; i < maxLen; i++) {
            List<MatchResult> row = new ArrayList<>(inputMatrixSize);
            for (int j = 0; j < inputMatrixSize; j++) {
                row.add(inputMatrix.get(j).get(i));
            }
            rotatedMatrix.add(row);
        }

        return null;
    }

    @AllArgsConstructor
    @Value
    private class MatchResult {
        int start, end;
        String value;
    }
}
