package pers.ljy.background.web.vo;

import java.io.Serializable;

public abstract class BasicVo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//主键ID
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
