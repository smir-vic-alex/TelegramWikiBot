package admin.service;

import com.admin.wiki.tlg.bot.rest.services.AdminService;
import com.business.BusinessConfig;
import com.telegram.api.bot.BotService;
import com.telegram.api.bot.TelegramApiModuleException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import ru.json.schema2pojo.dto.AddCommandRq;
import ru.json.schema2pojo.dto.CreateBotRq;
import ru.json.schema2pojo.dto.DefaultAnswer;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;


public class AdminServiceTest {

    @Mock
    private BotService botService;

    @Mock
    private BusinessConfig config;

    @InjectMocks
    private AdminService adminService;

    @BeforeTest
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBotCreate() {
        CreateBotRq botRq = new CreateBotRq();
        botRq.setName("botName");
        botRq.setToken("token");

        DefaultAnswer defaultAnswer = adminService.createBot(botRq);
        assertNotNull(defaultAnswer);
        assertEquals("success", defaultAnswer.getSuccess());

        doThrow(TelegramApiModuleException.class).when(botService).register(any(String.class), any(String.class));
        defaultAnswer = adminService.createBot(botRq);
        assertNotNull(defaultAnswer);
        assertEquals("fail", defaultAnswer.getSuccess());
    }

    @Test
    public void testCommandCreate() {
        AddCommandRq commandRq = new AddCommandRq();
        commandRq.setCommand("command");
        commandRq.setValue("value");

        DefaultAnswer defaultAnswer = adminService.addCommand(commandRq);
        assertNotNull(defaultAnswer);
        assertEquals("success", defaultAnswer.getSuccess());

        doThrow(TelegramApiModuleException.class).when(config).getCommandsMap();
        defaultAnswer = adminService.addCommand(commandRq);
        assertNotNull(defaultAnswer);
        assertEquals("fail", defaultAnswer.getSuccess());
    }

}
