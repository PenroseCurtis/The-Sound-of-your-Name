/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalproject;
import javax.sound.midi.*;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author curtis
 */
public class MidiHelper {
     
     
     int temp=0;
        public  void playSomething(String input) throws MidiUnavailableException, InterruptedException {
            
            //Synthesizer init
            Synthesizer synth = MidiSystem.getSynthesizer();
            long startTime = System.nanoTime();
            synth.open();
            long estimatedTime = System.nanoTime() - startTime;
            
            //Variable Init
            int length;
            int[] CMajorNotes;
            int[] BluesScaleNotes;
            int[] ChromaticNotes;
            int[] notes;
            CMajorNotes = new int[8];
            CMajorNotes[0]=60; //C
            CMajorNotes[1]=62; //D
            CMajorNotes[2]=64; //E
            CMajorNotes[3]=65; //F
            CMajorNotes[4]=67; //G
            CMajorNotes[5]=69; //A
            CMajorNotes[6]=71; //B
            CMajorNotes[7]=72; //C
            
            BluesScaleNotes = new int[7];
            BluesScaleNotes[0] =60;
            BluesScaleNotes[1] =63;
            BluesScaleNotes[2] =65;
            BluesScaleNotes[3] =66;
            BluesScaleNotes[4] =67;
            BluesScaleNotes[5] =70;
            BluesScaleNotes[6] =72;
            
            ChromaticNotes = new int[13];
            for(int i=0;i<13;i++)
            {
                ChromaticNotes[i]= 60+i;
            }
            
                    
            
            Scale BluesScale = new Scale(7, BluesScaleNotes);
            Scale CMajor = new Scale(8,CMajorNotes);
            Scale Chromatic = new Scale(13, ChromaticNotes);
            
            
            length = input.length();
            
            //Creating array of notes to be played
            //Change the scale to play different ones
           int[] notesToPlay = this.notesToPlay(CMajor, input);
            
            MidiChannel[] midiChannel = synth.getChannels();
            Instrument[] instruments = synth.getDefaultSoundbank().getInstruments();
            boolean successloadingInstruments = synth.loadInstrument(instruments[0]);
            
           
            
            
            for(int i=0;i<length;i++)
            {
            midiChannel[0].noteOn(notesToPlay[i], 100);
            System.out.println(notesToPlay[i]);
            TimeUnit.MILLISECONDS.sleep(500);
            }
            //Playing it backwards
            /*for(int i=length-1;i>=0;i--)
            {
                midiChannel[0].noteOn(notes[i],100);
                System.out.println(notes[i]);
                TimeUnit.MILLISECONDS.sleep(500);
                
            }*/
        
        }
        public int[] notesToPlay(Scale myScale, String input) 
        {
            int[] notes = new int[input.length()];
             for(int i=0;i<input.length();i++)
            {
                int temp = input.charAt(i)%myScale.getNumnotes();
                notes[i]= myScale.get(temp);
            }
             return notes;
        }
        
}
