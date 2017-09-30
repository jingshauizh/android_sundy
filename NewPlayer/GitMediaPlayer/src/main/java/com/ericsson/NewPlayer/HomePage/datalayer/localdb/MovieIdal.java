package com.ericsson.NewPlayer.HomePage.datalayer.localdb;

import android.support.annotation.Nullable;

import com.ericsson.NewPlayer.HomePage.datalayer.model.MovieModel;
import com.ericsson.NewPlayer.HomePage.datalayer.model.MovieModel_Table;
import com.ericsson.NewPlayer.HomePage.movie.MovieType;
import com.ericsson.NewPlayer.HomePage.util.ValidationUtil;
import com.raizlabs.android.dbflow.sql.language.Select;

import java.util.List;

/**
 * Created by eqruvvz on 1/19/2017.
 */
public class MovieIdal  {

    public synchronized void save(MovieModel movieModel) {
        ValidationUtil.validate(movieModel);
        saveValid(movieModel);
    }


    public synchronized void saveAll(final List<MovieModel> movieModelsList) {
        ValidationUtil.pruneInvalid(movieModelsList);
        if (movieModelsList.isEmpty()) {
            return;
        }

        for (MovieModel infoObjectModel : movieModelsList) {
            saveValid(infoObjectModel);
        }


    }

    private void saveValid(MovieModel movieModel) {
        MovieModel existing = loadById(movieModel.getId());
        if (existing == null) {
            movieModel.save();

        } else {
            movieModel.setId(existing.getId());
            movieModel.update();
        }
    }

    @Nullable
    public synchronized MovieModel loadById(Integer id) {

        return new Select().from(MovieModel.class)
                .where(MovieModel_Table.id.eq(id))
                .querySingle();
    }


    public synchronized List<MovieModel> loadAll() {
        List<MovieModel> dbMovieModelLsit = new Select().from(MovieModel.class).queryList();
        return dbMovieModelLsit;
    }

    public synchronized List<MovieModel> loadAllByType(MovieType mMovieType) {
        List<MovieModel> dbMovieModelLsit = new Select().from(MovieModel.class).where(MovieModel_Table.tags.like("%"+mMovieType.name()+"%")).queryList();
        return dbMovieModelLsit;
    }


}
