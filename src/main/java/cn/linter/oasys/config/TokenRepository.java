package cn.linter.oasys.config;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;

public class TokenRepository extends JdbcDaoSupport implements PersistentTokenRepository {

    TokenRepository() {
    }

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update("insert into user_token (username, series, token, last_used) values(?,?,?,?)",
                    token.getUsername(), token.getSeries(), token.getTokenValue(), token.getDate());
        }
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update("update user_token set token = ?, last_used = ? where series = ?", tokenValue, lastUsed, series);
        }
    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        try {
            if (this.getJdbcTemplate() != null) {
                return this.getJdbcTemplate().queryForObject("select username,series,token,last_used from user_token where series = ?",
                        (rs, rowNum) -> new PersistentRememberMeToken(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4)), seriesId);
            }
        } catch (EmptyResultDataAccessException exception1) {
            if (this.logger.isDebugEnabled())
                this.logger.debug("查询series '" + seriesId + "' 的Token没有返回任何结果。", exception1);
        } catch (IncorrectResultSizeDataAccessException exception2) {
            this.logger.error("查询series '" + seriesId + "' 的Token返回多个值，但series应该是唯一的。", exception2);
        } catch (DataAccessException exception3) {
            this.logger.error("无法为series '" + seriesId + "' 加载Token。", exception3);
        }
        return null;
    }

    @Override
    public void removeUserTokens(String username) {
        if (this.getJdbcTemplate() != null) {
            this.getJdbcTemplate().update("delete from user_token where username = ?", username);
        }
    }

}
