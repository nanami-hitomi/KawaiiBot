package me.alexflipnote.kawaiibot.commands

import com.github.natanbc.weeb4j.image.NsfwFilter
import me.alexflipnote.kawaiibot.KawaiiBot
import me.alexflipnote.kawaiibot.extensions.clean
import me.alexflipnote.kawaiibot.utils.Helpers
import me.aurieh.ichigo.core.CommandContext
import me.aurieh.ichigo.core.ICommand
import me.aurieh.ichigo.core.annotations.Command
import net.dv8tion.jda.core.Permission

@Command(description = "Tickle someone! :3", botPermissions = [Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_ATTACH_FILES])
class Tickle : ICommand {
    override fun run(ctx: CommandContext) {
        val m = ctx.args.asMember

        if (m == null || m.user.idLong == ctx.author.idLong) {
            ctx.channel.sendFile(Helpers.getImageStream("images/tickle.gif"), "tickle.gif").queue()
        } else if (m.user.idLong == ctx.jda.selfUser.idLong) {
            ctx.send("*giggles* ❤")
        } else {
            //TODO: Implement tickle
            ctx.send("**${m.effectiveName.clean()}**, you got tickled by **${ctx.author.name}**")
        }
    }
}
