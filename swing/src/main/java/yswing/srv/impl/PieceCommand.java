package yswing.srv.impl;

import yswing.srv.Cmd;
import yswing.srv.Command;
import yswing.ui.HtmlUtils;

@Cmd(name = "piece")
public class PieceCommand implements Command {

    @Override
    public String exec(String... args) {
        if(args.length == 0){
            return HtmlUtils.htmlRed("should at least have one argument.");
        }
        String opt = args[0];
        return HtmlUtils.htmlRed("success");
    }
}
