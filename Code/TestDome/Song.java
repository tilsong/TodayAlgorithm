package TestDome;

public class Song {
    private String name;
    private Song nextSong;

    public Song(String name) {
        this.name = name;
    }

    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }

    public boolean isInRepeatingPlaylist() {
        Song cur = this;
        Song next = this.nextSong;

        while (next != null && next.nextSong != null) {
            if (next == cur || next.nextSong == cur) {
                return true;
            }
            cur = cur.nextSong;
            next = next.nextSong.nextSong;
        }

        return false;
    }

    public static void main(String[] args) {
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");

        first.setNextSong(second);
        second.setNextSong(first);

        System.out.println(first.isInRepeatingPlaylist());
    }
}