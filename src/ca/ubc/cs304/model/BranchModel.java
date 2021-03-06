package ca.ubc.cs304.model;

/**
 * The intent for this class is to update/store information about a single branch
 */
public class BranchModel implements Comparable {
    private final int id;
    private final String name;
    private final String address;

    public BranchModel(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return getName();
    }


    @Override
    public int compareTo(Object o) {
        if (o instanceof BranchModel) {
            return Integer.compare(this.id, ((BranchModel) o).id);
        }
        return 0;
    }
}
