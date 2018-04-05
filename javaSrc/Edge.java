
public class Edge  <T extends Object> {

private T vertex1;
private T vertex2;
	
public Edge(T vertex1, T vertex2) 
{
	this.vertex1 = vertex1;
	this.vertex2 = vertex2;
}

public T getVertex1() {
	return vertex1;
}

public void setVertex1(T vertex1) {
	this.vertex1 = vertex1;
}

public T getVertex2() {
	return vertex2;
}

public void setVertex2(T vertex2) {
	this.vertex2 = vertex2;
}
	
public String toString() {
	
	return String.format("%s-%s", vertex1, vertex2);
	}

}
