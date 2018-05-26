package graphic;

import com.badlogic.gdx.scenes.scene2d.Group;

public abstract class GroupExtension extends Group implements Comparable<Object>{
	protected Integer z=0;

	@Override
	public int compareTo(Object arg0) {
		if(GroupExtension.class.isAssignableFrom(arg0.getClass()))
		{
			return this.z.compareTo(((GroupExtension)arg0).z);
		}
		else if(ActorExtension.class.isAssignableFrom(arg0.getClass()))
		{
			return this.z.compareTo(((ActorExtension)arg0).z);
		}
		return 0;
	}

	public Integer getZ() {
		return z;
	}

	public void setZ(Integer z) {
		this.z = z;
	}
}
