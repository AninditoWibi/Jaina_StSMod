package jaina.cards;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import jaina.cards.optionCards.CounterAttack;
import jaina.cards.optionCards.CounterCurse;
import jaina.cards.optionCards.CounterDamage;
import jaina.modCore.IHelper;
import jaina.modCore.JainaEnums;

import java.util.ArrayList;

public class Counterspell extends AbstractArcaneCard {

    public static final String ID = IHelper.makeID("Counterspell");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 2;

    public Counterspell() {
        super(ID, false, CARD_STRINGS, COST, CardType.POWER, JainaEnums.JAINA_COLOR,
                CardRarity.RARE, CardTarget.SELF);
        this.isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> choices = new ArrayList<>();
        // 添加三张选项卡牌
        choices.add(new CounterAttack());
        choices.add(new CounterDamage());
        choices.add(new CounterCurse());
        // 释放选择的效果
        addToBot(new ChooseOneAction(choices));
    }

    @Override
    public void upp() {
        upgradeDescription(CARD_STRINGS);
        this.isEthereal = false;
        this.exhaust = true;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Counterspell();
    }
}
