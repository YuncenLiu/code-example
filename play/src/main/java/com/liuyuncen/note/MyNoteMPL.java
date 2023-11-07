package com.liuyuncen.note;

import javax.sound.midi.*;

/**
 * @belongsProject: 测试平台
 * @belongsPackage: com.liuyuncen.note
 * @author: Xiang想
 * @createTime: 2023-11-03  12:39
 * @description: TODO
 * @version: 1.0
 */
public class MyNoteMPL {
    public static void main(String[] args) throws Exception {
        Sequencer player = MidiSystem.getSequencer();
        Sequence sequence = new Sequence(Sequence.PPQ, 4);
        player.setSequence(sequence);
        Track track = sequence.createTrack();

        addNote(track,ShortMessage.NOTE_ON,6,60,100,16);
        System.out.println("1");
        addNote(track,ShortMessage.NOTE_ON,6,60,100,2);
        System.out.println("1");
        addNote(track,ShortMessage.NOTE_ON,6,60,100,5);
        System.out.println("1");
        addNote(track,ShortMessage.NOTE_ON,6,60,100,10);
        System.out.println("1");
        addNote(track,ShortMessage.NOTE_ON,6,60,100,16);
        System.out.println("1");
        addNote(track,ShortMessage.NOTE_ON,6,60,100,20);

        player.open();
        // 1 分钟 60 节拍
        player.setTempoInBPM(60);
        player.start();
    }

    public static void addNote(Track track,int command, int channel,int note,int volume,int tick) throws Exception{
        ShortMessage shortMessage = new ShortMessage(command, channel, note, volume);
        MidiEvent midiEvent = new MidiEvent(shortMessage, tick);
        track.add(midiEvent);
    }
}
