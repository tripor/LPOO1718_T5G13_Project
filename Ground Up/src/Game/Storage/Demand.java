package Game.Storage;

import java.util.ArrayList;
import java.util.List;

public class Demand {
	
	public List<Object> _request_by = new ArrayList<Object>();
	
	public void push(Object who_request) {
		_request_by.add(who_request);
	}

	public Demand() {
		
	}

}
