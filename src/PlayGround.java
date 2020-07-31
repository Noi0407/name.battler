
import jobs.BasePlayer;
import jobs.Brave;
import jobs.Fighter;
import jobs.Knight;
import jobs.Priest;
import jobs.Villager;
import jobs.Wizard;
import party_operation.Party;
import party_operation.RandomSelect;
import party_operation.RecoveryFiast;
import party_operation.ReduceLowDefEnemy;
import party_operation.ReduceLowHpEnemy;
import party_operation.ReduceStrongEnemy;
import party_operation.BaseOperation;
import party_operation.OperationType;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Comparator;

public class PlayGround {

    Scanner stdin = new Scanner(System.in);
    Random random = new Random();

    Party party1 = new Party();
    Party party2 = new Party();

    int ONE_PARTY_NUMBERS = 3;

    String PARTY_NAME_FIRST = "パーティ1";
    String PARTY_NAME_SECOND = "パーティ2";

    List<BasePlayer> allPlayers = new ArrayList<>();
    List<BasePlayer> sortAgiPlayers = new ArrayList<>();

    int MAX_TURN = 20;

    public void start() {

        setupPlayerNum();

        inputPartyData(PARTY_NAME_FIRST, party1);
        inputPartyData(PARTY_NAME_SECOND, party2);

        stdin.close();

        setAllPlayerList(party1, party2, allPlayers);

        // 素早さ順に並び替えたリストを作成
        sortAgiPlayers.addAll(allPlayers);
        sortAgi(sortAgiPlayers);

        startMsg();

        battle();

        result();

    }

    private void setupPlayerNum() {

        System.out.println("何人ずつで戦いますか？");
        ONE_PARTY_NUMBERS = inputPlayerNum();
    }

    private int inputPlayerNum() {

        String pNumStr = stdin.next();

        try {
            int playerNum = Integer.parseInt(pNumStr);

            if (notGoodNum(playerNum)) {
                System.out.println("無効な数字のため、COMが自動で設定します。");
                playerNum = random.nextInt(3) + 1;
            }

            return playerNum;

        } catch (Exception e) {

            System.out.println("数字で入力してください。");
            pNumStr = stdin.next();

        }
        return random.nextInt(3) + 1;
    }

    private boolean notGoodNum(int playerNum) {
        return playerNum <= 0 || playerNum >= 10 ? true : false;
    }

    private void inputPartyData(String PARTY_NAME, Party party) {

        for (int i = 1; i <= ONE_PARTY_NUMBERS; i++) {

            String playerName = inputName(PARTY_NAME, i);
            int jobType = inputJob(playerName);

            party.appendPlayer(createPlayer(playerName, jobType));
        }

        party.setOperation(inputOperation(PARTY_NAME));

    }

    private BasePlayer createPlayer(String playerName, int jobType) {

        switch (jobType) {
            case 0:
                return new Fighter(playerName);
            case 1:
                return new Wizard(playerName);
            case 2:
                return new Priest(playerName);
            case 3:
                return new Knight(playerName);
            case 4:
                return new Brave(playerName);

            case 9:
                return new Villager(playerName);

            default:
                return new Fighter(playerName);
        }
    }

    // 名前の入力
    private String inputName(String PARTY_NAME, int i) {

        System.out.println(PARTY_NAME + "の" + i + "人目の名前を入力してください。");
        return stdin.next();
    }

    private int inputJob(String playerName) {

        System.out.println(playerName + "の職業を入力してください。");
        System.out.println("0:戦士 1:魔法使い 2:僧侶 3 騎士 4:勇者");
        String jobStr = stdin.next();

        try {
            int jobType = Integer.parseInt(jobStr);
            return jobType;

        } catch (Exception e) {
            System.out.println("数字で入力してください。");
            jobStr = stdin.next();
        }

        return 0;
    }

    private BaseOperation inputOperation(String PARTY_NAME) {

        System.out.printf("\n" + PARTY_NAME + "の作戦を選択してください。\n");

        // 説明を表示
        OperationType.printDescription();

        int operationType = scanOperationType();

        System.out.println("");

        // どの作戦が選ばれたか判断して返す
        BaseOperation operation = decisionOperation(operationType);

        return operation;
    }

    private int scanOperationType() {

        String opeStr = stdin.next();

        try {

            int operationType = Integer.parseInt(opeStr);
            return operationType;

        } catch (Exception e) {

            System.out.println("数字で入力してください。");
            opeStr = stdin.next();

        }

        return 0;
    }

    private BaseOperation decisionOperation(int operationType) {

        switch (operationType) {
            case 0:
                return new RandomSelect();
            case 1:
                return new RecoveryFiast();
            case 2:
                return new ReduceLowHpEnemy();
            case 3:
                return new ReduceLowDefEnemy();
            case 4:
                return new ReduceStrongEnemy();

            default:
                return new RandomSelect();
        }

    }

    private void setAllPlayerList(Party party1, Party party2, List<BasePlayer> allPlayers) {
        allPlayers.addAll(party1.getMembers());
        allPlayers.addAll(party2.getMembers());
    }

    // ソート
    private void sortAgi(List<BasePlayer> sortAgiPlayers2) {

        Collections.sort(sortAgiPlayers2, new Comparator<BasePlayer>() {

            @Override
            public int compare(BasePlayer playerFirst, BasePlayer playerSecond) {
                return Integer.compare(playerSecond.getAgi(), playerFirst.getAgi());
            }
        });
    }

    // ステータスの表示
    private void printAllStatus() {
        printPartyStatus(PARTY_NAME_FIRST, party1);
        printPartyStatus(PARTY_NAME_SECOND, party2);
    }

    private void printPartyStatus(String PARTY_NAME, Party party) {

        System.out.println("-" + PARTY_NAME + "-");

        for (BasePlayer p : party.getMembers()) {
            p.PrintStatus();
        }
    }

    private void startMsg() {

        printAllStatus();

        System.out.println("");
        System.out.println("=== バトル開始 ===");
    }

    // バトル
    private void battle() {

        int nowTurn = 1;
        // 最大ターンまで
        while (untilTurnMax(nowTurn)) {

            System.out.println("--------------------------------");
            System.out.printf("- ターン%d -\n", nowTurn);

            // 作成した人数分素早さ順に攻撃を行う
            for (int i = 0; i < sortAgiPlayers.size(); i++) {

                BasePlayer attacker = sortAgiPlayers.get(i);
                BasePlayer defender = getDefender(attacker);

                if (attacker.canAct()) {
                    attacker.attack(attacker, defender);
                }

                if (attacker.isPoison()) {
                    attacker.receivePoisonEffect();
                }

                // 倒れたプレイヤーは削除する
                diedPlayerRemove();

                // どちらかのパーティーが壊滅していたらバトル終了
                if (somePartyLost())
                    return;

                System.out.println("");
            }

            System.out.println("");
            printAllStatus();

            nowTurn = nowTurn + 1;
        }
    }

    // 最大20ターン
    private boolean untilTurnMax(int nowTurn) {
        return nowTurn <= MAX_TURN ? true : false;
    }

    // ディフェンダーを取得
    private BasePlayer getDefender(BasePlayer attacker) {

        // ディフェンダーがいるパーティーは？
        Party defenseParty = getDefenseParty(attacker);

        // ディフェンダーを決めて返す
        BasePlayer defender = attacker.decideDefender(defenseParty);

        return defender;
    }

    private Party getDefenseParty(BasePlayer attacker) {

        // パーティー1の中にattackerが居たら
        return party1.getMembers().contains(attacker) ? party2 : party1;
    }

    // 倒れたプレイヤーを消す
    private void diedPlayerRemove() {

        List<BasePlayer> removePlayers = new ArrayList<>();

        for (BasePlayer p : allPlayers) {
            if (p.getHp() <= 0) {
                removePlayers.add(p);
            }
        }

        for (BasePlayer p : removePlayers) {
            removeAllList(p);
        }
    }

    private void removeAllList(BasePlayer player) {

        if (party1.getMembers().contains(player)) {
            party1.removePlayer(player);
        } else {
            party2.removePlayer(player);
        }

        sortAgiPlayers.remove(player);
        allPlayers.remove(player);
    }

    // どちらかのパーティーのサイズが0ならtrueが返ってくる
    private boolean somePartyLost() {
        return party1.getMembers().size() <= 0 || party2.getMembers().size() <= 0;
    }

    // 結果発表
    private void result() {
        System.out.println("");

        String winPartyName = getWinPartyName();
        System.out.println(winPartyName + "の勝利...!!");
    }

    // size()が大きいほうのパーティーネームを返す。
    private String getWinPartyName() {
        return party1.getMembers().size() >= party2.getMembers().size() ? PARTY_NAME_FIRST : PARTY_NAME_SECOND;
    }
}