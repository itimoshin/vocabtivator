package org.bubbasmith.vocabtivator.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MatrixUtil {

//    private <T> List<List<T>> addMissedElementsToRows(List<List<T>> inputMatrix) {
//        // Adjust all rows to the same size (by adding nulls)
//        int maxLen = inputMatrix.stream().map(List::size).max(Integer::compareTo).orElse(0);
//        for (List<T> row : inputMatrix) {
//            for (int i = 0; i < row.size() - maxLen; i++) {
//                row.add(null);
//            }
//        }
//        return inputMatrix;
//    }
//
//    private <T> List<List<T>> rotateMatrix(List<List<T>> inputMatrix) {
//        // Rotate the matrix
//        int inputMatrixSize = inputMatrix.size();
//        int columnSize = inputMatrix.get(0).size();
//        List<List<T>> rotatedMatrix = new ArrayList<>(columnSize);
//        for (int i = 0; i < columnSize; i++) {
//            List<T> row = new ArrayList<>(inputMatrixSize);
//            for (int j = 0; j < inputMatrixSize; j++) {
//                row.add(inputMatrix.get(j).get(i));
//            }
//            rotatedMatrix.add(row);
//        }
//        return rotatedMatrix;
//    }

    public  <T> List<List<T>> allCombinations(List<List<T>> matrix) {
        int rowsCount = matrix.size();
        List<Integer> rowsSizes = matrix.stream().map(List::size).collect(Collectors.toList());
        List<Integer> shiftsArray = matrix.stream().map(it -> 0).collect(Collectors.toList());
        int totalCombinations = rowsSizes.stream().reduce(1, (acc, curr) -> acc*curr);
        List<List<Integer>> resultShiftArray = new ArrayList<>(totalCombinations);

        int currentShiftIndex = 0;
        int startShiftIndex = 0;

        while (true) {
            System.out.println(shiftsArray);
            resultShiftArray.add(shiftsArray);

            // if current shift hit the max value. I.e. 0(c)000->4(c)000
            if(Objects.equals(shiftsArray.get(currentShiftIndex), rowsSizes.get(currentShiftIndex) - 1)) {

                // set current shift back to 0, i.e. 4(c)000->0(c)000
                 shiftsArray.set(currentShiftIndex, 0);

                for (int i = currentShiftIndex + 1; i < shiftsArray.size(); i++) {
                    shiftsArray.set(i, 0);
                }

                // if we can move to the next shift - do it and +1, i.e. 0(c)000->01(c)00
                if(currentShiftIndex < shiftsArray.size() - 1) {
                    currentShiftIndex++;
                    shiftsArray.set(currentShiftIndex, shiftsArray.get(currentShiftIndex ) + 1);
                } else {
                    // otherwise,
                    // if we can move startShift, shift from the beginning again (+1 for the start shift and move shift to the next),
                    if (startShiftIndex < rowsCount - 1 && shiftsArray.get(startShiftIndex) < rowsSizes.get(startShiftIndex) - 1) {
                        shiftsArray.set(startShiftIndex, shiftsArray.get(startShiftIndex) + 1);
                        startShiftIndex++;
                        currentShiftIndex = startShiftIndex;
                    } else {
                        System.out.println("--------");
                        // else start shifting from zero (startShit=0) from the beginning and increment it
                        Optional<Integer> latestNonMaxShiftOpt = IntStream.rangeClosed(0, shiftsArray.size()-1)
                                .boxed()
                                .sorted(Collections.reverseOrder())
                                .filter(i -> shiftsArray.get(i) < rowsSizes.get(i) - 1 && i < shiftsArray.size() - 1)
                                .findFirst();
                        if (latestNonMaxShiftOpt.isEmpty()) {
                            return IntStream.range(0, resultShiftArray.size()).mapToObj(i ->
                                    IntStream.range(0, resultShiftArray.get(i).size())
                                            .mapToObj(j -> matrix.get(j).get(resultShiftArray.get(i).get(j)))
                                            .collect(Collectors.toList()))
                                    .collect(Collectors.toList());
                        } else {
                            int shiftsArrayNextStartVal = shiftsArray.get(latestNonMaxShiftOpt.get()) + 1;
                            startShiftIndex = latestNonMaxShiftOpt.get();
                            currentShiftIndex = latestNonMaxShiftOpt.get() + 1;
                            setZerosAfterIdx(shiftsArray, latestNonMaxShiftOpt.get());
                            shiftsArray.set(latestNonMaxShiftOpt.get(), shiftsArrayNextStartVal);
                        }


                    }

                }



            } else {
                shiftsArray.set(currentShiftIndex, shiftsArray.get(currentShiftIndex) + 1);
            }
        }
    }

    private void setZerosAfterIdx(List<Integer> list, int idx) {
        for (int i = idx + 1; i < list.size(); i++) {
            list.set(i,0);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
        matrix.add(Arrays.asList(1,2,3,4));
        matrix.add(Arrays.asList(4,5,6,4));
        matrix.add(Arrays.asList(7,8,9,5));
        matrix.add(Arrays.asList(10,11,12,5));

        List<List<Integer>> result = new MatrixUtil().allCombinations(matrix);
        System.out.println();

    }

}
