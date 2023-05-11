package Programmers.카카오;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 방금그곡 {

    public static void main(String[] args) {
        String m = "ABCDEFG";
        String[] musicinfos = {	"11:50,12:04,HELLO,CDEFGAB", "12:57,13:11,BYE,CDEFGAB"};

        System.out.println(solution(m, musicinfos));
    }

    public static String solution(String m, String[] musicinfos) {
        List<MusicInfo> musicInfoList = new ArrayList<>();

        for (int i = 0; i < musicinfos.length; i++) {
            String[] split = musicinfos[i].split(",");
            int runningTime = timeTranslate(split[0], split[1]);
            String lyric = split[3];
            String unSharpLyric = sharpTranslate(lyric);
            int iter = runningTime / unSharpLyric.length();
            String totalLyric = "";
            for (int j = 0; j < iter; j++) {
                totalLyric += unSharpLyric;
            }
            totalLyric += unSharpLyric.substring(0, runningTime % unSharpLyric.length());

            // 변환 m
            m = sharpTranslate(m);

            if (totalLyric.contains(m)) {
                musicInfoList.add(new MusicInfo(runningTime, split[2]));
            }
        }

        if (musicInfoList.size() != 0) {
            Collections.sort(musicInfoList);
            return musicInfoList.get(0).name;
        }
        return "(None)";
    }

    static String sharpTranslate(String lyric){
        lyric = lyric.replaceAll("C#", "c");
        lyric = lyric.replaceAll("D#", "d");
        lyric = lyric.replaceAll("F#", "f");
        lyric = lyric.replaceAll("G#", "g");
        lyric = lyric.replaceAll("A#", "a");

        return lyric;
    }

    static int timeTranslate(String startTime, String endTime){
        String[] startSplit = startTime.split(":");
        String[] endSplit = endTime.split(":");

        int startHour = Integer.parseInt(startSplit[0]);
        int startMin = Integer.parseInt(startSplit[1]);
        int endHour = Integer.parseInt(endSplit[0]);
        int endMin = Integer.parseInt(endSplit[1]);

        int startTotal = (startHour * 60) + startMin;
        int endTotal = (endHour *60) + endMin;

        return endTotal-startTotal;
    }

    static class MusicInfo implements Comparable<MusicInfo>{
        int runningTime;
        String name;

        public MusicInfo(int runningTime, String name) {
            this.runningTime = runningTime;
            this.name = name;
        }

        @Override
        public int compareTo(MusicInfo o) {
            return o.runningTime - this.runningTime;
        }
    }
}
