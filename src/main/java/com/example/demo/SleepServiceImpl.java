@Service
public class SleepServiceImpl implements DelayService {
    @Override
    public int sleep(int seconds) {
	
		Thread.sleep(seconds * 1000);
		return seconds+1;
	}
}