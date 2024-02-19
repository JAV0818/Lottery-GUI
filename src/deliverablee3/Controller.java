package deliverablee3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author julianvazquez
 */
public class Controller {

    private final Model model;
    private final View view;

    public Controller(View view) {
        model = new Model();
        this.view = view;
    }

    public String doLotteryDrawings(String userInput, String userDrawings) {

        //Check points that will be ensuring the input is valid
        //There are 8 different cases in which the input can be invalid
        boolean checkpoint1 = true;
        boolean checkpoint2 = true;
        boolean checkpoint3 = true;
        boolean checkpoint4 = true;
        boolean checkpoint5 = true;
        boolean checkpoint6 = true;
        boolean checkpoint7 = true;
        boolean checkpoint8 = true;

        //first check to ensure that there no null 
        if (userInput.isEmpty()) {
            //if empty then the condition is false
            checkpoint1 = false;
        } else {
            //check for leading or trailing spaces
            if (userInput.charAt(0) == ' ' || userInput.charAt(userInput.length() - 1) == ' ') {
                checkpoint2 = false;
            } else {
                String[] elements = userInput.split("\\s+");

                if (elements.length < 6 || elements.length > 6) {
                    checkpoint3 = false;
                } else {
                    for (String element : elements) {
                        boolean regexCheck = true;
                        boolean numberCheck = true;

                        if (!element.matches("\\d") && !element.matches("\\d\\d")) {
                            regexCheck = false;
                        } else {
                            Integer number = Integer.parseInt(element);

                            if (number < 1 || number > 60) {
                                numberCheck = false;
                            }
                        }

                        checkpoint4 = regexCheck && numberCheck;
                    }
                }
            }
        }

        //5-8 check point, ensures the number of drawings entered is valid
        //if the value of the input is empty, then input is not valid
        if (userDrawings.isEmpty()) {
            checkpoint5 = false;
        } else {
            //second condition for the second input, checks spaces
            if (userDrawings.charAt(0) == ' ' || userDrawings.charAt(userDrawings.length() - 1) == ' ') {
                checkpoint6 = false;
            } else {
                String[] elements = userDrawings.split("\\s+");
                if (elements.length != 1) {
                    checkpoint7 = false;
                } else {
                    boolean regexCheck = true;
                    boolean numberCheck = true;

                    if (!elements[0].matches("\\d+")) {
                        regexCheck = false;
                    } else {
                        Integer number2 = Integer.parseInt(elements[0]);

                        if (number2 < 1 || number2 > 100000) {
                            numberCheck = false;
                        }
                    }

                    checkpoint8 = regexCheck && numberCheck;
                }
            }
        }

        boolean finalCheck = true;
        String errorMsg = "";
        //returns message based on the value of the checkpoint booleans
        if (!checkpoint1 || !checkpoint2 || !checkpoint3 || !checkpoint4) {
            errorMsg += "Invalid input: " + userInput + "\n";
            finalCheck = false;
            if (!checkpoint1) {
                errorMsg += "User numbers should not be empty\n";
            } else if (!checkpoint2) {
                errorMsg += "User numbers should not lead or end with any spaces\n";
            } else if (!checkpoint3) {
                errorMsg += "User numbers should only have 6 elements\n";
            } else if (!checkpoint4) {
                errorMsg += "Each of the 6 elements in User numbers should be an integer "
                        + "in the range 1 through 60 inclusive \n";
            }
        }
        //returns message based on the validity of the second input
        if (!checkpoint5 || !checkpoint6 || !checkpoint7 || !checkpoint8) {
            errorMsg += "\nInvalid input: " + userDrawings + "\n";
            finalCheck = false;

            if (!checkpoint5) {
                errorMsg += "User drawing should not be an empty";
            } else if (!checkpoint6) {
                errorMsg += "User drawing should not begin nor end with any spaces";
            } else if (!checkpoint7) {
                errorMsg += "User drawing should only be 1 element";
            } else if (!checkpoint8) {
                errorMsg += "User drawing only can be integer between 1 and 100000";
            }
        }
        //return error message
        if (!finalCheck) {
            view.showErrorMsg(errorMsg);
            return "";

        } else { //returns the drawings
            String result = "You entered: " + userInput;
            int drawingTimes = Integer.parseInt(userDrawings);

            int[] frequencies = getFrequencies(userInput, drawingTimes);

            result += getSummaryStatements(frequencies);

            return result;
        }
    }

    private String getSummaryStatements(int[] freq) {
        String statements = "";
        for (int i = 0; i < freq.length; i++) {
            statements += "\n" + freq[i] + " drawings matched " + i + " of your numbers.";
        }

        return statements;
    }

    private int[] getFrequencies(String userNums, int occurrences) {
        int[] freq = new int[7];

        for (int i = 1; i <= occurrences; i++) {
            HashSet<Integer> lottoDrawings = model.doOneDrawing();

            List<Integer> matches = findMatches(userNums, lottoDrawings);

            freq[matches.size()]++;
        }

        return freq;
    }

    private List<Integer> findMatches(String userInput, HashSet<Integer> lotteryDrawing) {
        //puts input in a int list
        List<Integer> inputList = new ArrayList<>();
        //stores the input into string
        String[] ints = userInput.split("\\s+");

        //iterates through the string of ints and converts into ints and adds to a list
        for (String intsString : ints) {
            int integer = Integer.parseInt(intsString);
            inputList.add(integer);
        }
        //storage of matches
        List<Integer> matchedList = new ArrayList<>();
        //iterate through input list
        for (Integer userNumber : inputList) {
            //if there are matches, then add to matched list
            if (lotteryDrawing.contains(userNumber)) {
                matchedList.add(userNumber);
            }
        }

        return matchedList;
    }

}
