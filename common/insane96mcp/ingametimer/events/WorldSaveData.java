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
	
	private long timeOffset = 0;
	
	public long getTimeOffset() {
		return this.timeOffset;
	}
	
	public void setTimeOffset(long timeOffset) {
		this.timeOffset = timeOffset;
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
		setTimeOffset(nbt.getLong("timeOffset"));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		compound.setLong("timeOffset", this.timeOffset);
		return compound;
	}

}
