/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package deliverablee3;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author julianvazquez
 */
public class Model {

    private final Set<Integer> results;

    public Model() {
        results = new HashSet<>();
    }

    public HashSet<Integer> doOneDrawing() {

        HashSet<Integer> generatedLotto = new HashSet<>();

        while (generatedLotto.size() < 6) {
            int randomDrawing = (int) (Math.random() * 60) + 1;
            generatedLotto.add(randomDrawing);
        }

        return generatedLotto;
    }

}
