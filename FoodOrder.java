public class FoodOrder {
    private int memberID;
    private String foodOrder; // A, B, C, or D
    private int priority;

    // constructor
    public FoodOrder(int memberID, String foodOrder) {
        this.memberID = memberID;
        this.foodOrder = foodOrder;

        if (memberID >= 8001 && memberID <= 8199) {
            priority = 1;
        } else if (memberID >= 8200 && memberID <= 8999) {
            priority = 2;
        } else {
            priority = 3;
        }
    }

    // provide methods getter, setter, toString ....
    public int getMemberID() {
        return memberID;
    }

    public void setMemberID(int memberID) {
        this.memberID = memberID;
    }

    public String getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(String foodOrder) {
        this.foodOrder = foodOrder;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String toString() {
        return "[ MemberID: " + memberID + " ordered Set " + foodOrder + " with priority " + priority + " ]" + "\n";
    }
}
