package juststudy.springadvanced.common;

import lombok.extern.slf4j.Slf4j;

/**
 * @author : jomg
 * @description :
 * @packageName : juststudy.springadvanced.common
 * @fileName : ServiceImpl
 * @date : 9/25/24
 */
@Slf4j
public class ServiceImpl implements Service {

    @Override
    public void save() {
        log.info("save!");
    }

    @Override
    public void find() {
        log.info("find!");
    }
}
