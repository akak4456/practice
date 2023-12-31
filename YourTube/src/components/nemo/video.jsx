import React, { useState, memo } from 'react';
import YouTube from 'react-youtube';
import styles from './video.module.css';
import { COLORS } from '../../common/colors';

const Video = memo(
  ({ video, isLargerSize, flexWidthRatio, flexHeightRatio, nemoPlayer, darkTheme }) => {
    const [play, setPlay] = useState(false);
    const [vol, setVol] = useState(50);
    const [YT, setYT] = useState();
    const [playing, setPlaying] = useState(false);
    const onPlay = () => {
      setPlaying(false);
      setPlay((play) => !play);
    };
    const opts = {
      height: '100%',
      width: '100%',
      playerVars: {
        // https://developers.google.com/youtube/player_parameters
        autoplay: 0,
        modestbranding: 1,
      },
    };
    const onReady = (e) => {
      e.target.playVideo();
      e.target.setVolume(40);
      setYT(e.target);
    };
    const changeVolume = (e) => {
      setVol(e.target.value);
      YT.setVolume(e.target.value);
    };
    const onChange = (e) => {
      setVol(YT.getVolume());
      console.log(YT.getPlayerState());
      YT.getPlayerState() === 1 ? setPlaying(true) : setPlaying(false);
    };
    return (
      <div
        style={{
          width: flexWidthRatio + '%',
          height: flexHeightRatio + '%',
          padding: '0.2em 0.35em',
          display: 'flex',
          flexFlow: 'column',
        }}
      >
        <div
          className={styles.container}
          style={{
            border:
              (playing && `solid 3px ${COLORS.mainColorL}`) ||
              (darkTheme && '1px rgb(50, 50, 50, 0.5) solid') ||
              '1px rgb(200, 200, 200, 0.5) solid',
          }}
        >
          {play && (
            <div>
              <YouTube
                className={styles.videoPlayer}
                videoId={video.videoId}
                opts={opts}
                onReady={onReady}
                onStateChange={onChange}
              />
              <input
                type="range"
                min="0"
                max="100"
                value={vol}
                className={styles.volume}
                onChange={changeVolume}
              />
              <button className={styles.closePlay} onClick={onPlay}>
                x
              </button>
            </div>
          )}
          <div className={styles.over}>
            <div className={styles.title}>{video.snippet.title}</div>
            <div
              className={styles.overIcon}
              style={{
                fontSize: isLargerSize ? `1.5em` : '1.1em',
              }}
            >
              <div className={styles.iconFlex}>
                <i className="fas fa-play-circle" onClick={onPlay} />
                <span>재생</span>
              </div>
              <div className={styles.iconFlex}>
                <i className="far fa-clone" onClick={() => nemoPlayer(video)} />
                <span>플레이어로 재생</span>
              </div>
            </div>
          </div>
          <img
            className={styles.img}
            src={
              isLargerSize
                ? video.snippet.thumbnails.high.url
                : video.snippet.thumbnails.medium.url
            }
            style={{
              objectFit: isLargerSize && 'scale-down',
            }}
            alt="thumnail"
          />
        </div>
        <div
          className={styles.title}
          style={{ color: darkTheme ? COLORS.Lgrey2 : COLORS.fontGrey }}
        >
          {playing && (
            <i
              className="fas fa-circle"
              // className="fas fa-volume-up"
              style={{
                color: `${COLORS.mainColorL}`,
                marginRight: '0.5em',
                fontSize: '0.3em',
              }}
            />
          )}
          {video.snippet.title}
        </div>
      </div>
    );
  }
);

export default Video;
