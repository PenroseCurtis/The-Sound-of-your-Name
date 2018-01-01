/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;

/**
 *
 * @author curtis
 */
public class Scale {
    int noteNum;
    int[] notes;
    Scale(int numberOfNotes, int[] theNotes)
    {
        noteNum=numberOfNotes;
        notes= theNotes;
    }
    int get(int index)
    {
        return notes[index];
    }
    int getNumnotes()
    {
        return noteNum;
    }
}
