package dev.danae.creativesuite.plugin.commands.hotbar;

import dev.danae.commons.commands.CommandContext;
import dev.danae.commons.commands.CommandException;
import dev.danae.commons.commands.CommandUsageException;
import dev.danae.commons.parser.ParserException;
import dev.danae.creativesuite.model.Manager;
import dev.danae.creativesuite.plugin.commands.ManagerCommand;
import java.util.List;
import java.util.Map;


public class HotbarRemoveCommand extends ManagerCommand
{
  // Constructor
  public HotbarRemoveCommand(Manager manager)
  {
    super(manager, "creativesuite.hotbar.remove");
  }
    
  
  // Handle the command
  @Override
  public void handle(CommandContext context) throws CommandException, CommandUsageException
  {
    try
    {
      // Validate the number of arguments
      if (!context.hasArgumentsCount(1))
        throw new CommandUsageException();
      
      // Create a scanner for the arguments
      var scanner = context.getArgumentsScanner();
      
      // Parse the arguments
      var key = scanner.nextNamespacedKey();
      
      var hotbar = this.getManager().getHotbar(key);
      if (hotbar == null)
        throw new CommandException(this.formatMessage("hotbar-not-found", Map.of("name", key)));

      // Remove the hotbar
      this.getManager().removeHotbar(key);

      // Send a message about the removed hotbar
      context.sendMessage(this.formatMessage("hotbar-removed", Map.of("name", key)));
    }
    catch (ParserException ex)
    {
      throw new CommandException(ex.getMessage(), ex);
    }
  }
  
  // Handle tab completion of the command
  @Override
  public List<String> handleTabCompletion(CommandContext context)
  {
    if (context.hasArgumentsCount(1))
      return this.handleHotbarTabCompletion(context.getArgument(0));
    else
      return List.of();
  }
}
