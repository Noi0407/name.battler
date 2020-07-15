import java.util.ArrayList;
import jobs.BasePlayer;

public class Party {

    // memberというアレイリストを持っている。インスタンスで作成。
    private ArrayList<BasePlayer> members;

    Party() {
        members = new ArrayList<BasePlayer>();
    }

    ArrayList<BasePlayer> getMembers() {
        return members;
    }

    /**
     * パーティーにプレイヤーを追加する
     * 
     * @param player : 追加するプレイヤー
     */
    public void appendPlayer(BasePlayer player) {
        members.add(player);
    }

    /**
     * パーティーからプレイヤーを離脱させる
     * 
     * @param player : 離脱させるプレイヤー
     */
    public void removePlayer(BasePlayer player) {
        members.remove(members.indexOf(player));
    }

}