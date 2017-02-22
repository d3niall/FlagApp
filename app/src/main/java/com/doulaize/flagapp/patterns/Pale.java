package com.doulaize.flagapp.patterns;

import com.doulaize.flagapp.exception.CoordinateException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rdeleuze on 2/16/17.
 */
public class Pale {

    private Integer mMinCoordinate;
    private Integer mMaxCoordinate;
    private List<Integer> mCoordinates;
    private List<Integer> mSecondaryCoordinates;
    private Boolean isRegular;

    void Pale(Integer aMin, Integer aMax) {

        mMinCoordinate = aMin;
        mMaxCoordinate = aMax;
        mCoordinates = new ArrayList<Integer>();
    }

    void addPoint(Integer mNewCoordinate) {

        if (mNewCoordinate > mMaxCoordinate || mNewCoordinate < mMinCoordinate) {
            throw new CoordinateException(mNewCoordinate, mMinCoordinate, mMaxCoordinate);
        }

        int position;
        for (position = 0; position < mCoordinates.size(); position++) {
            if (mCoordinates.get(position) > mNewCoordinate)
                break;
        }

        mCoordinates.add(position, mNewCoordinate);

    }

    void addPoint(boolean evenOutResult) {

        Integer newCoord = (Integer) (((mCoordinates.size() > 0 ? mCoordinates.get(mCoordinates.size() - 1) : mMinCoordinate) + mMaxCoordinate) / 2);
        mCoordinates.add(newCoord);

        if (evenOutResult)
            makeRegular();

    }

    void makeRegular() {
        Integer step = (Integer) ((mMaxCoordinate - mMinCoordinate) / (1 + mCoordinates.size()));
        for (int i = 0; i < mCoordinates.size(); i++) {
            mCoordinates.set(i, (i + 1) * step);
        }
    }


    public Integer getMinCoordinate() {
        return mMinCoordinate;
    }

    public void setMinCoordinate(Integer minCoordinate) {
        mMinCoordinate = minCoordinate;
    }

    public Integer getMaxCoordinate() {
        return mMaxCoordinate;
    }

    public void setMaxCoordinate(Integer maxCoordinate) {
        mMaxCoordinate = maxCoordinate;
    }

    public List<Integer> getCoordinates() {
        return mCoordinates;
    }

    public void setCoordinates(List<Integer> coordinates) {
        mCoordinates = coordinates;
    }

    public Boolean getRegular() {
        return isRegular;
    }

    public void setRegular(Boolean regular) {
        isRegular = regular;
    }
}