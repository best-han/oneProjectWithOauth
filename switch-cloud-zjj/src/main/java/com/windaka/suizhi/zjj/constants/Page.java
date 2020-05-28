package com.windaka.suizhi.zjj.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象
 * @author hjt
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> implements Serializable {

	private static final long serialVersionUID = -275582248840137389L;
	private int totalRows;
	private int currentPage;
	private List<T> list;

}
