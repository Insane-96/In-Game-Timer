package insane96mcp.ingametimer.events;

import insane96mcp.ingametimer.InGameTimer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class WorldSaveData extends WorldSavedData {

	private static final String DATA_NAME = InGameTimer.MOD_ID;

	public WorldSaveData() {
		super(DATA_NAME);
	}
	
	public WorldSaveData(String name) {
		super(name);
	}
	
	private long timeStart = 0;
	
	public long getTimeStart() {
		return this.timeStart;
	}
	
	public void setTimeStart(long timeOffset) {
		this.timeStart = timeOffset;
		markDirty();
	}
	
	private long timeStop = 0;
	
	public long getTimeStop() {
		return this.timeStop;
	}
	
	public void setTimeStop(long timeStop) {
		this.timeStop = timeStop;
		markDirty();
	}
	
	public static WorldSaveData get(World world) {
		MapStorage storage = world.getMapStorage();
		WorldSaveData instance = (WorldSaveData) storage.getOrLoadData(WorldSaveData.class, DATA_NAME);
		
		if (instance == null) {
			instance = new WorldSaveData();
			storage.setData(DATA_NAME, instance);
		}
		
		return instance;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		setTimeStart(nbt.getLong("timeOffset"));
		setTimeStop(nbt.getLong("timeStop"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setLong("timeOffset", this.timeStart);
		compound.setLong("timeStop", this.timeStop);
		return compound;
	}

}
