package Jaina.cards;

import Jaina.ModCore.IHelper;
import Jaina.ModCore.JainaEnums;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Flamestrike extends AbstractJainaCard {
    public static final String ID = IHelper.makeID("Flamestrike");
    private static final CardStrings CARD_STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final int COST = 1;

    public Flamestrike() {
        super(ID, false, CARD_STRINGS, COST, CardType.ATTACK, JainaEnums.JAINA_COLOR,
                CardRarity.COMMON, CardTarget.ALL_ENEMY);
        setDamage(6);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dealAoeDamage(AbstractGameAction.AttackEffect.FIRE);
        dealAoeDamage(AbstractGameAction.AttackEffect.FIRE);
        IHelper.getTempCard(new Burn());
    }

    @Override
    public void upp() {
        upgradeDamage(2);
    }

    @Override
    public AbstractCard makeCopy() {
        return new Flamestrike();
    }
}