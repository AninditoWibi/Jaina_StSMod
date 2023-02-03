package jaina.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import jaina.modCore.JainaEnums;

import java.util.ArrayList;

public class FrostAffinityEndAction extends AbstractGameAction {

    private static void setFree(ArrayList<AbstractCard> cards) {
        for (AbstractCard c : cards) {
            if (c.hasTag(JainaEnums.CardTags.FROST) && c.isCostModifiedForTurn) {
                System.out.println(c.cost);
                c.setCostForTurn(c.cost);
                c.isCostModifiedForTurn = false;
                System.out.println(c.costForTurn);
            }
        }
    }

    @Override
    public void update() {
        setFree(AbstractDungeon.player.hand.group);
        setFree(AbstractDungeon.player.discardPile.group);
        setFree(AbstractDungeon.player.drawPile.group);
        setFree(AbstractDungeon.player.exhaustPile.group);
        this.isDone = true;
    }
}
