package party_operation;

import java.util.ArrayList;
import jobs.BasePlayer;

public class Party {

    // memberというアレイリストを持っている。インスタンスで作成。
    private ArrayList<BasePlayer> members;
    //partyの作戦
    private BaseOperation partyOperation;

    public Party() {
        this.members = new ArrayList<BasePlayer>();
    }

    public void setPartyOperation(BaseOperation operation) {
        this.partyOperation = operation;
    }

    public ArrayList<BasePlayer> getMembers() {
        return this.members;
    }

    public BaseOperation getPartyOperation(){
        return this.partyOperation;
    }

    //playerに作戦を覚えさせる
    

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