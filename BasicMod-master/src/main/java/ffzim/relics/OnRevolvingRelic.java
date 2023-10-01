package ffzim.relics;


import com.megacrit.cardcrawl.cards.AbstractCard;

public interface OnRevolvingRelic
{
    void onRevolving(AbstractCard card, int persistCount);
}