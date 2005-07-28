package metamodel;

import metamodel.ActionBlock;
import metamodel.Expression;

/**
 * ConditionalAlternative pairs a condition with its action-block.
 * A list of ConditionalAlternatives is used to represent if / elseif
 * / else chains.
 *
 * @author npiggin
 * @author sjr
 */
public class ConditionalAlternative {
    protected Expression condition;
    protected ActionBlock block;
    
    public ConditionalAlternative(Expression c, ActionBlock b) {
        setCondition(c);
        setBlock(b);
    }

    public Expression getCondition() {
        return condition;
    }

    public void setCondition(Expression condition) {
        this.condition = condition;
    }

    public ActionBlock getBlock() {
        return block;
    }

    public void setBlock(ActionBlock block) {
        this.block = block;
    }
}