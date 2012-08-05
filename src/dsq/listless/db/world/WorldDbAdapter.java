package dsq.listless.db.world;

public interface WorldDbAdapter {
    World fetch();
    boolean update(World world);
}
