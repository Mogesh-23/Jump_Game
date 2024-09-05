package stringall;


import java.util.*;

class JugState {
    int jug1;
    int jug2;

    JugState(int jug1, int jug2) {
        this.jug1 = jug1;
        this.jug2 = jug2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof JugState) {
            JugState other = (JugState) obj;
            return this.jug1 == other.jug1 && this.jug2 == other.jug2;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(jug1, jug2);
    }
}

public class jumpgame {

    public static boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int target) {
        if (target == 0) {
            return true;
        }

        if (target > jug1Capacity + jug2Capacity) {
            return false;
        }

        Queue<JugState> queue = new LinkedList<>();
        Set<JugState> visited = new HashSet<>();

        queue.add(new JugState(0, 0));
        visited.add(new JugState(0, 0));

        while (!queue.isEmpty()) {
            JugState current = queue.poll();

            if (current.jug1 == target || current.jug2 == target || current.jug1 + current.jug2 == target) {
                return true;
            }

            List<JugState> nextStates = new ArrayList<>();

            nextStates.add(new JugState(jug1Capacity, current.jug2));
            nextStates.add(new JugState(current.jug1, jug2Capacity));
            nextStates.add(new JugState(0, current.jug2));
            nextStates.add(new JugState(current.jug1, 0));

            int pourJug1ToJug2 = Math.min(current.jug1, jug2Capacity - current.jug2);
            nextStates.add(new JugState(current.jug1 - pourJug1ToJug2, current.jug2 + pourJug1ToJug2));

            int pourJug2ToJug1 = Math.min(current.jug2, jug1Capacity - current.jug1);
            nextStates.add(new JugState(current.jug1 + pourJug2ToJug1, current.jug2 - pourJug2ToJug1));

            for (JugState nextState : nextStates) {
                if (!visited.contains(nextState)) {
                    queue.add(nextState);
                    visited.add(nextState);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter capacity of Jug 1: ");
        int jug1Capacity = sc.nextInt();

        System.out.print("Enter capacity of Jug 2: ");
        int jug2Capacity = sc.nextInt();

        System.out.print("Enter target amount: ");
        int target = sc.nextInt();

        boolean result = canMeasureWater(jug1Capacity, jug2Capacity, target);

        System.out.println(result ? "Yes, you can measure the target amount." : "No, it's not possible to measure the target amount.");

        sc.close();
    }
}
