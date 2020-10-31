package prototype.deep_clone;

import java.io.Serializable;

public class DeepCloneableTarget implements Serializable, Cloneable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String cloneName;
	private String cloneClass;

	public DeepCloneableTarget(String cloneName, String cloneClass) {
		this.cloneName = cloneName;
		this.cloneClass = cloneClass;
	}

	// 仅需浅拷贝即可
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
