package work.skills.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 构建树结构
 * </p>
 *
 */
public class BuildTree {
	
	public static<T> Tree<T> build(List<Tree<T>> nodes){
		
		if(nodes == null) {
			return null;
		}
		
		List<Tree<T>> topNodes = new ArrayList<>();
		
		for (Tree<T> children : nodes) {
			String pid = children.getParentId();
			// 根节点下面的一级节点
			if(pid == null || "0".equals(pid)) {
				topNodes.add(children);
				continue;
			}
			// 父子节点关联
			for (Tree<T> parent : nodes) {
				String id = parent.getId();
				if(id != null && id.equals(pid)) {
					parent.getChildren().add(children);
					parent.setHasChildren(true);
					children.setHasParent(true);
					continue;
				}
			}
		}
		Tree<T> root = new Tree<T>();
		if(topNodes.size() == 1) {
			root = topNodes.get(0);
		}else {
			root.setId("0");
			root.setParentId("");
			root.setChecked(true);
			root.setHasChildren(true);
			root.setChildren(topNodes);
			root.setHasParent(false);
			root.setText("根目录");
		}
		return root;
	}
}
