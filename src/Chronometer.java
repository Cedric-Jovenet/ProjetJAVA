public class Chronometer {
    private long startTime;
    private long stopTime;
    private boolean running;

    // Start the chronometer
    public void start() {
        if (!running) {
            startTime = System.currentTimeMillis();
            running = true;
        }
    }

    // Stop the chronometer
    public void stop() {
        if (running) {
            stopTime = System.currentTimeMillis();
            running = false;
        }
    }

    // Reset the chronometer
    public void reset() {
        startTime = 0;
        stopTime = 0;
        running = false;
    }

    // Get the elapsed time in seconds
    public long getSecondsElapsed() {
        long elapsedTime = running ? System.currentTimeMillis() - startTime : stopTime - startTime;
        return elapsedTime / 1000;
    }

    // Get the elapsed time in minutes
    public long getMinutesElapsed() {
        return getSecondsElapsed() / 60;
    }

    // Check if the chronometer is running
    public boolean isRunning() {
        return running;
    }

    // Get a formatted string for elapsed time
    public String getFormattedElapsedTime() {
        long seconds = getSecondsElapsed();
        long minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
