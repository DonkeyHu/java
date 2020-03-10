package work.skills.tree;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 *  一、树结构实体类（适用于文件）:1.当页面需要展示树结构时，2.同时你的输入数据仅仅是List<File>这种拥有父子节点的列表时，
 *  那么可以通过BuildTree和Tree构建一颗树返回出去
 *  二、像xml这种sql方式居然也能返回出树结构
 * </p>
 *
 */
public class Tree<T> {
	
	/**
	 * 节点ID
	 */
	private String id;
	/**
	 * 显示节点文本
	 */
	private String text;
	/**
	 * 节点是否被选中 true false
	 */
	private boolean checked = false;
	/**
	 * 父ID
	 */
	private String parentId;
	/**
	 * 是否有父节点
	 */
	private boolean hasParent = false;
	/**
	 * 是否有子节点
	 */
	private boolean hasChildren = false;
	
	private boolean directory;
	
	private String fileDirPath;
	
	/**
	 * 节点的子节点
	 */
	private List<Tree<T>> children = new ArrayList<Tree<T>>();

	
	
	public Tree() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public List<Tree<T>> getChildren() {
		return children;
	}
	public void setChildren(List<Tree<T>> children) {
		this.children = children;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public boolean isHasParent() {
		return hasParent;
	}
	public void setHasParent(boolean hasParent) {
		this.hasParent = hasParent;
	}
	public boolean isHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
	public String getFileDirPath() {
		return fileDirPath;
	}
	public void setFileDirPath(String fileDirPath) {
		this.fileDirPath = fileDirPath;
	}
	public boolean isDirectory() {
		return directory;
	}
	public void setDirectory(boolean directory) {
		this.directory = directory;
	}
}
