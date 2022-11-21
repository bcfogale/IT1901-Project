package rest;

import java.io.IOException;

import org.springframework.stereotype.Service;

import core.Leaderboard;
import json.FileManagement;

@Service
public class CleanEService {
    private FileManagement fileManagement;
    private Leaderboard leaderboard;

    public CleanEService(Leaderboard leaderboard) {
        this.fileManagement = new FileManagement();
        this.leaderboard = leaderboard;
    }
    public CleanEService() {
        this.fileManagement = new FileManagement();
        this.leaderboard = new Leaderboard();
    }

    public void save() throws IOException {
        this.fileManagement.writeToFile(this.leaderboard);
    }

    public Leaderboard getLeaderboard() {
        return this.leaderboard;
    }

    public void setLeaderboard(Leaderboard leaderboard) throws IOException {
        this.leaderboard = leaderboard;
        this.save();
    }

    
    
}
