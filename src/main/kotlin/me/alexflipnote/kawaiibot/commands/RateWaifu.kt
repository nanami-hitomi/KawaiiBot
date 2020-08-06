package me.alexflipnote.kawaiibot.commands

import me.alexflipnote.kawaiibot.extensions.clean
import me.aurieh.ichigo.core.CommandContext
import me.aurieh.ichigo.core.ICommand
import me.aurieh.ichigo.core.annotations.Command
import me.aurieh.ichigo.utils.StringUtil
import java.util.*

@Command(description = "Rates your waifu~", aliases = ["rate"])
class RateWaifu : ICommand {
    var r = Random()

    override fun run(ctx: CommandContext) {
        if (ctx.argString.isEmpty()) {
            ctx.send("You have to rate something..?")
            return
        }

        val m = ctx.args.asMember
        val rn = Random()

        when {
            m == null ->{
                val text=StringUtil.cleanerContent(ctx.argString)
                random.setSeed(text.hashCode())
                ctx.send("I'd rate `${}` a **${r.nextInt(100 - 1) + 1} / 100**")
                }
            m.user.idLong == ctx.jda.selfUser.idLong ->
                ctx.send("I'd rate me a **110/100!**")
            m.user.idLong == 173529942431236096L && ctx.author.idLong == 173529942431236096L ->
                ctx.send("Hitoccchi, I'd rate you a **100/100!**")
            m.user.idLong == 173529942431236096L ->
                ctx.send("I'd rate hitoccchi a **100/100!**")
            m.user.idLong == ctx.author.idLong ->   {
                random.setSeed(ctx.author.idLong)
                ctx.send("I'd rate you a **${rn.nextInt(90 - 1) + 11} / 100**")
                }
            else -> {
                random.setSeed(m.user.idLong)
                    ctx.send("I'd rate `${m.effectiveName.clean()}` a **${rn.nextInt(90 - 1) + 11} / 100**")
                }
            }
        }
        
    }

