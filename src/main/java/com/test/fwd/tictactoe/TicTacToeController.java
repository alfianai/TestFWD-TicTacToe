package com.test.fwd.tictactoe;

import com.test.fwd.tictactoe.model.Game;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("game")
public class TicTacToeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(@ModelAttribute("game") Game game) {
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String markTile(
            @ModelAttribute("game") Game game,
            @RequestParam("tile_id") String tileId,
            @RequestParam(value = "board_size", required = false, defaultValue = "3") int boardSize,
            @RequestParam(value = "new_game", required = false, defaultValue = "false") boolean newGame,
            @RequestParam(value = "vs_player", required = false, defaultValue = "false") boolean vsPlayer,
            @RequestParam(value = "player_go_first", required = false, defaultValue = "false") boolean playerGoFirst
   ) {

        if (newGame) {
            game.reset(boardSize);
            game.setVsPlayer(vsPlayer);
            game.setPlayerGoFirst(playerGoFirst);
            if (!playerGoFirst && !vsPlayer) {
                game.markTileRandom(); // Computer Turn
            }
        } else {
            game.markTile(tileId); // Player Turn

            if (!vsPlayer) {
                game.markTileRandom(); // Computer Turn
            }
        }
        game.getBoard();
        return "index";
    }

    @ModelAttribute("game")
    public Game populateGame() {
        return new Game();
    }
}
