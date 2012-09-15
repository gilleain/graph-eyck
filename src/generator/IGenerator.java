package generator;

import diagram.element.IDiagramElement;
import draw.ParameterSet;

public interface IGenerator<T> {
	
	public IDiagramElement generate(T obj);
	
	public void setParams(ParameterSet params);

}
