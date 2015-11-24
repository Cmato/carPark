package cz.muni.fi.pa165.service;

import cz.muni.fi.pa165.enums.RentalState;

/**
 *
 * @author xhubeny2
 */
public class RentalStateTransition {

    private RentalState startState;
    private RentalState endState;

    public RentalStateTransition(RentalState startState, RentalState endState) {
        this.startState = startState;
        this.endState = endState;
    }

    @Override
    public int hashCode() {
        final int prime = 47;
        int result = 1;
        result = prime * result
                + ((endState == null) ? 0 : endState.hashCode());
        result = prime * result
                + ((startState == null) ? 0 : startState.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RentalStateTransition other = (RentalStateTransition) obj;
        if (endState != other.endState) {
            return false;
        }
        if (startState != other.startState) {
            return false;
        }
        return true;
    }
}
