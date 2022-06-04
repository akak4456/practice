import React, { useEffect, useRef, useState, memo } from 'react';
import styles from './Block.module.css';

const Block = memo(({ id, blockContent }) => {
  return (
    <div id={id} className={styles.block}>
      <p>{blockContent}</p>
    </div>
  );
});

export default Block;
